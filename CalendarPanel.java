import greenfoot.*;
import java.util.ArrayList;

public class CalendarPanel extends Actor {
    private User currentUser;
    private int width, height;
    private ArrayList<CalendarDay> days = new ArrayList<>();
    private ArrayList<Actor> calendarElements = new ArrayList<>(); // NEW: track all elements
    private CalendarMonth calendarMonth;
    private ButtonActor prevBtn, nextBtn;
    private TextLabel monthLabel;
    
    public CalendarPanel(User user, int w, int h) {
        currentUser = user;
        width = w;
        height = h;
        calendarMonth = new CalendarMonth();
        createPanel();
    }
    
    private void createPanel() {
        GreenfootImage img = new GreenfootImage(width, height);
        
        // Clean white background like Google Calendar
        img.setColor(new greenfoot.Color(255, 255, 255));
        img.fill();
        
        // Subtle border
        img.setColor(new greenfoot.Color(220, 220, 220));
        img.drawRect(0, 0, width-1, height-1);
        
        setImage(img);
    }
    
    protected void addedToWorld(World world) {
        createHeader(world);
        createCalendar(world);
    }
    
    private void createHeader(World world) {
        // Previous month button
        prevBtn = new ButtonActor("<", 255, 255, 255, 0, 0, 128, 40, 40) {
            public void onClick() {
                calendarMonth.prevMonth();
                refreshCalendar();
            }
        };
        world.addObject(prevBtn, getX() - 180, getY() - (height/2) + 30);
        calendarElements.add(prevBtn); // Track it
        
        // Month and Year label
        monthLabel = new TextLabel(
            calendarMonth.getMonthName() + " " + calendarMonth.getYear(),
            28,
            new greenfoot.Color(0, 0, 128),
            300, 40,
            new greenfoot.Color(0, 0, 0, 0)
        );
        world.addObject(monthLabel, getX(), getY() - (height/2) + 30);
        calendarElements.add(monthLabel); // Track it
        
        // Next month button
        nextBtn = new ButtonActor(">", 255, 255, 255, 0, 0, 128, 40, 40) {
            public void onClick() {
                calendarMonth.nextMonth();
                refreshCalendar();
            }
        };
        world.addObject(nextBtn, getX() + 180, getY() - (height/2) + 30);
        calendarElements.add(nextBtn); // Track it
        
        // Day labels (Sun, Mon, Tue, etc.)
        String[] dayNames = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        int labelY = getY() - (height/2) + 70;
        int cellW = width / 7;
        
        for (int i = 0; i < 7; i++) {
            TextLabel dayLabel = new TextLabel(
                dayNames[i],
                16,
                new greenfoot.Color(100, 100, 100),
                cellW, 30,
                new greenfoot.Color(0, 0, 0, 0)
            );
            int x = getX() - width/2 + i * cellW + cellW/2;
            world.addObject(dayLabel, x, labelY);
            calendarElements.add(dayLabel); // Track it
        }
    }
    
    private void createCalendar(World world) {
        int rows = 6;
        int cols = 7;
        int cellW = width / cols;
        int cellH = (height - 120) / rows;
        
        int daysInMonth = calendarMonth.getDaysInMonth();
        int firstDay = calendarMonth.getFirstDayOfWeek();
        int currentDay = calendarMonth.getCurrentDay();
        
        int dayNumber = 1;
        
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int cellIndex = r * cols + c;
                
                if (cellIndex >= firstDay && dayNumber <= daysInMonth) {
                    boolean isToday = (dayNumber == currentDay);
                    CalendarDay day = new CalendarDay(dayNumber, currentUser, isToday);
                    
                    int x = getX() - width/2 + c * cellW + cellW/2;
                    int y = getY() - (height/2) + 110 + r * cellH + cellH/2;
                    
                    world.addObject(day, x, y);
                    days.add(day);
                    calendarElements.add(day); // Track it
                    dayNumber++;
                }
            }
        }
    }
    
    public void refreshCalendar() {
        World world = getWorld();
        if (world == null) return;
        
        // Remove old days
        for (CalendarDay day : days) {
            if (day.getWorld() != null) {
                world.removeObject(day);
                calendarElements.remove(day);
            }
        }
        days.clear();
        
        // Update month label
        if (monthLabel != null && monthLabel.getWorld() != null) {
            world.removeObject(monthLabel);
            calendarElements.remove(monthLabel);
        }
        
        monthLabel = new TextLabel(
            calendarMonth.getMonthName() + " " + calendarMonth.getYear(),
            28,
            new greenfoot.Color(0, 0, 128),
            300, 40,
            new greenfoot.Color(0, 0, 0, 0)
        );
        world.addObject(monthLabel, getX(), getY() - (height/2) + 30);
        calendarElements.add(monthLabel);
        
        // Recreate calendar
        createCalendar(world);
    }
    
    // NEW: Method to clean up all calendar elements
    public void removeAllElements() {
        World world = getWorld();
        if (world == null) return;
        
        for (Actor element : calendarElements) {
            if (element.getWorld() != null) {
                world.removeObject(element);
            }
        }
        calendarElements.clear();
        days.clear();
    }
    
    public ArrayList<CalendarDay> getDays() {
        return days;
    }
}
