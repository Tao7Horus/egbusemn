// 
// Decompiled by Procyon v0.5.30
// 

package connect;

import portal.community.poll.datamodel.PollResult;
import portal.community.poll.datamodel.PollView;
import portal.community.poll.datamodel.PollList;
import portal.community.poll.datamodel.PollItem;
import java.util.Vector;
import portal.community.poll.datamodel.PollInfo;
import common.Log;
import javax.naming.InitialContext;
import portal.community.poll.session.PollHome;
import portal.community.poll.session.Poll;

public class PollConn
{
    private Poll poll_remote;
    private PollHome poll_Home;
    
    public PollConn() {
        this.poll_remote = null;
        this.poll_Home = null;
        try {
            this.poll_Home = (PollHome)new InitialContext().lookup("Poll");
            this.poll_remote = this.poll_Home.create();
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
    }
    
    public int PollAddItem(final PollInfo pollInfo, final Vector vector) {
        int insertPoll = 0;
        final PollItem pollItem = new PollItem(pollInfo, vector);
        try {
            insertPoll = this.poll_remote.InsertPoll(pollItem);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return insertPoll;
    }
    
    public PollList pollList(final int n, final int n2, final int n3, final String s) {
        PollList itemList = null;
        try {
            itemList = this.poll_remote.itemList(n, n2, n3, s);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemList;
    }
    
    public PollList adpollList(final int n, final int n2, final int n3, final String s, final int n4) {
        PollList aditemList = null;
        try {
            aditemList = this.poll_remote.aditemList(n, n2, n3, s, n4);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return aditemList;
    }
    
    public PollView pollView(final int n, final int n2) {
        PollView itemView = null;
        try {
            itemView = this.poll_remote.itemView(n, n2);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemView;
    }
    
    public PollView pollmainView(final int n) {
        PollView pollitemView = null;
        try {
            pollitemView = this.poll_remote.pollitemView(n);
        }
        catch (Exception ex) {
            System.out.println("Servlet:" + ex);
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return pollitemView;
    }
    
    public int pollok(final PollInfo pollInfo, final Vector vector) {
        int pollOk = 0;
        final PollItem pollItem = new PollItem(pollInfo, vector);
        try {
            pollOk = this.poll_remote.pollOk(pollItem);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return pollOk;
    }
    
    public int AddVote2(final int n, final int n2, final int n3, final String s) {
        int vote2 = 0;
        try {
            vote2 = this.poll_remote.Vote2(n, n2, n3, s);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return vote2;
    }
    
    public PollList pollListSearch(final int n, final int n2, final int n3, final String s, final String s2, final String s3) {
        PollList itemListSearch = null;
        try {
            itemListSearch = this.poll_remote.itemListSearch(n, n2, n3, s, s2, s3);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemListSearch;
    }
    
    public int AddVote(final int n, final int n2, final int n3, final String s) {
        int vote = 0;
        try {
            vote = this.poll_remote.Vote(n, n2, n3, s);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return vote;
    }
    
    public PollResult resultpoll(final int n, final int n2) {
        PollResult resultView = null;
        try {
            resultView = this.poll_remote.ResultView(n, n2);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return resultView;
    }
}
