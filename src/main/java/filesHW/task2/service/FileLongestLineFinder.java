package filesHW.task2.service;

import filesHW.task2.model.FileLineInfo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class FileLongestLineFinder {
    public Optional<FileLineInfo> findLongestLine(String filePath) throws IOException {
        return Files.lines(Path.of(filePath))
                .map(line->new FileLineInfo(line, line.length())).max((a,b) ->Integer.compare(a.length(), b.length()));
    }
}
