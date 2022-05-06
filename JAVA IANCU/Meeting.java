package ro.ase.acs.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Meeting implements Cloneable {
	private String name;
	private int startTime;
	private int endTime;
	private Priority priority;
	private String[] participants = null;
	
	public void setParticipants(String[] participants) throws CloneNotSupportedException {
		this.participants = new String[participants.length];
		System.arraycopy(participants, 0, this.participants, 0, participants.length);
	}
	
	public String[] getParticipants() throws CloneNotSupportedException {	
		if(participants != null) {
			return(String[]) Arrays.copyOf(participants, participants.length);
		}
		return participants;
	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Meeting(String name, int startTime, int duration) {
		
		this.name = name;
		this.startTime = startTime;
		this.endTime = startTime+duration;
		this.priority=Priority.low;
	}
	
	public Meeting(String name, int startTime, int duration,Priority prio,String[]participants) {
		
		this.name = name;
		this.startTime = startTime;
		this.endTime = startTime+duration;
		this.priority=prio;
		this.participants=participants;
	}

	public Meeting() {
		this.name="";
		this.startTime=0;
		this.endTime=0;
		this.priority=Priority.low;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(name);
		builder.append(", startTime=");
		builder.append(startTime);
		builder.append(", endTime=");
		builder.append(endTime);
		builder.append(", priority=");
		builder.append(priority);
		builder.append(", participants=");
		builder.append(participants);
		builder.append("]");
		return builder.toString();
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Meeting copy = (Meeting)super.clone();
		copy.name = name;
		copy.startTime = startTime;
		copy.endTime = endTime;
		copy.priority = priority;
		copy.participants = new String[this.participants.length];
		System.arraycopy(this.participants, 0, copy.participants, 0, this.participants.length);
		return copy;
	}
	
//	9. Create a public method with the following signature
//	boolean checkParticipant(String)
//which checks if the provided participant is in that meeting or not
	
	public boolean checkParticipant(String value) {
		if(Arrays.asList(participants).contains(value)) {
			return true;
		}
			return false;	 
	}

//	10. Concatenate two meetings by implementing the following public method
//	void concatenate(Meeting)
//The method concatenates the existing meeting with the new one in the following manner:
//	- the names will be concatenated and separated by a slash
//		(eg. "Meeting1" + "Meeting2" will become "Meeting1/Meeting2")
//	- the startTime will be the minimum of the two
//	- the endTime will be the maximum of the two
//	- the priority will be the highest one
//	- the participants will be the ones from both meetings,
//		but they should appear only once in the array

	public void concatenate(Meeting m) {
		this.name = this.name + "/"  + m.name;
		if(this.startTime > m.startTime) {
			this.startTime = m.startTime;
		}
		if(this.endTime < m.endTime) {
			this.endTime = m.endTime;
		}
		if(m.priority.compareTo(this.priority) > 0) {
			this.priority = m.priority;
		}
		Set<String> s = new HashSet<String>();
		for(String p: this.participants) {
			for(String p1:m.participants) {
				s.add(p);
				s.add(p1);
			}
		}
		this.participants = new String[s.size()];
		int j = 0;
		for(String p:s) {
			this.participants[j++] = p;
		}
		

	}
	}


