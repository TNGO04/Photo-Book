package album.util;

import java.io.FileNotFoundException;

/**
 * Public interface for parsing files.
 */
public interface FileParser {
  /**
   * Parse file.
   */
  void parse() throws FileNotFoundException, InterruptedException;
}

