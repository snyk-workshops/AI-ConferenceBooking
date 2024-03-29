package org.workshop.aiconferencebooking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.workshop.aiconferencebooking.model.Talk;
import org.workshop.aiconferencebooking.repository.TalkRepository;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TalkController {

    private final TalkRepository talkRepository;

    public TalkController(TalkRepository talkRepository) {
        this.talkRepository = talkRepository;
    }

    @GetMapping("/talks")
    public void displayTalks(
        @RequestParam String username, javax.servlet.http.HttpServletResponse response
    ) throws IOException {

        List<Talk> talks =  talkRepository.findBySpeakerUsername(username);

        response.setContentType("text/html");
        var writer = response.getWriter();
        buildTalksPage(username, talks, writer);
        writer.flush();
    }

    private void buildTalksPage(String username, List<Talk> talks, PrintWriter writer) throws IOException {

        String talksStr = talks.stream().map(
            talk -> String.format(talksTemplate, talk.getTitle(), talk.getDescription())
        ).collect(Collectors.joining());

        String usernameStr = "<h1>" + username + "'s talks<h1>";

        writer.write(String.format(pageTemplate, usernameStr, talksStr));
    }

    private String pageTemplate = """
            <html>
                <head lang="en">
                    <title>Java Conference</title>
                    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
                    <link href="/webjars/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet" media="screen" />
                    <link href="/css/style.css" rel="stylesheet" media="screen" />
                    <script src="/webjars/jquery/2.1.4/jquery.js"></script>
                    <script src="/webjars/bootstrap/3.3.4/js/bootstrap.js"></script>
                </head>
                <body>
                    <div class="container">
                        <div class="panel panel-default">
                            <div class="panel-heading">%s</div>
                            <div class="panel-body">%s</div>
                        </div>
                    </div>
                </body>
            </html>
    """;

    private String talksTemplate = """
            <div class="panel panel-default">
                <div class="panel-heading">%s</div>
                <div class="panel-body">%s</div>
            </div>
        """;
}
