package yo.hoo.support.rmi.exception;


@SuppressWarnings("serial")
public class UnFindRmiServiceException extends Exception {
	public UnFindRmiServiceException(String message) {
		super("找不到服务: " + message);
	}
}