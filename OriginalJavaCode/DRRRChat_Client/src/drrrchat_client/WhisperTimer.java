
package drrrchat_client;


// Referenced classes of package drrrchat_client:
//            WhisperMode

class WhisperTimer extends Thread
{

    public WhisperTimer(WhisperMode wm)
    {
        this.wm = wm;
    }

    public void run()
    {
        try
        {
            Thread.sleep(2000L);
            wm.allowspeak = true;
        }
        catch(Exception e) { }
    }

    WhisperMode wm;
}