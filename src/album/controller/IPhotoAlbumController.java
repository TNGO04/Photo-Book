package album.controller;

/**
 * Public interface for Controller.
 */
public interface IPhotoAlbumController {
  /**
   * Build photo album from instruction file.
   */
  void execute() throws Exception;

  /**
   * Initialize and then display/output view.
   */
  void setView() throws InterruptedException;
}
