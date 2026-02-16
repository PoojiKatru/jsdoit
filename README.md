 📋 Productivity Dashboard

A feature-rich productivity application built with Greenfoot (Java), offering an all-in-one solution for task management, school assignments, calendar events, notes, and music playback.

Presentation Component: https://docs.google.com/presentation/d/1MKikzdtWr9SryiFHf7CuYRkkyCyhahvkwuVySEZ6b4c/edit?usp=sharing

##  Features

###  **Dashboard Home**
- Personalized welcome screen with productivity overview
- Summary cards showing task and assignment statistics
- At-a-glance view of pending and completed items
- Integrated music player for focus sessions

### **Calendar**
- Interactive monthly calendar view
- Navigate between months with ease
- Add, view, and delete events for specific days
- Visual highlights for today's date
- Event popup for detailed day management

###  **Task Management**
- Create and organize personal tasks
- Four urgency levels: Low, Normal, High, Critical
- Visual urgency indicators with color coding
- Mark tasks as complete/incomplete
- Separate views for pending and completed tasks

###  **School Assignments**
- Dedicated panel for academic tasks
- Track assignments with teacher names
- Toggle completion status with visual feedback
- Separate tracking from personal tasks

###  **Notes** (Panel Component)
- Dedicated notes panel for quick capture
- Organized note management system

###  **Music Player**
- Built-in music selector for productivity
- Multiple track options (Lofi, Jazz, Lock In, IVY BGM)
- Stop/play controls
- Background music while working

##  Getting Started

### Prerequisites
- Java JDK 8 or higher
- [Greenfoot IDE](https://www.greenfoot.org/download) (version 3.0+)

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/productivity-dashboard.git
   cd productivity-dashboard
   ```

2. **Open in Greenfoot**
   - Launch Greenfoot IDE
   - Click `Scenario` → `Open...`
   - Navigate to the cloned directory and open the project

3. **Add Sound Files** (Optional)
   - Place audio files in the `sounds` folder:
     - `button-1.wav` - Button click sound
     - `click.wav` - Generic click sound
     - `success.wav` - Task completion sound
     - `delete.wav` - Delete action sound
     - `loginsuccess.wav` - Successful login
     - `loginfail.wav` - Failed login
     - `lofi.mp3`, `jazz.mp3`, `lockin.mp3`, `ivy.mp3` - Background music

4. **Run the Application**
   - Click the `Run` button in Greenfoot
   - The login screen will appear

## Usage

### First Time Setup
1. Click **Sign Up** to create a new account
2. Enter your email, password, and username
3. Click **Login** and enter your credentials

### Navigation
The sidebar provides quick access to all features:
- **Home** - Dashboard overview
- **Calendar** - Monthly calendar with events
- **Tasks** - Personal task list
- **School** - Academic assignments
- **Notes** - Note-taking panel
- **Log Out** - Return to login screen

### Managing Tasks
1. Navigate to the **Tasks** panel
2. Click **Add Task** button
3. Enter task name and select urgency level
4. Click on tasks to mark them complete
5. View completed tasks in the "Done" section

### Using the Calendar
1. Navigate to the **Calendar** panel
2. Use **<** and **>** buttons to change months
3. Click on any day to add or view events
4. Add events using the popup interface
5. Delete events by clicking the **×** button

### School Assignments
1. Navigate to the **School** panel
2. Add new assignments with teacher names
3. Click on assignment cards to toggle completion
4. Track your academic progress at a glance


### Core Components

#### **World Classes**
- `LoginWorld` - Authentication and user entry
- `DashboardWorld` - Main application interface
- `SignUpWorld` - New user registration *(not shown)*

#### **User Management**
- `User` - User data model *(not shown)*
- `UserManager` - Handles authentication and user storage
- `DataManager` - Persistent data storage *(not shown)*

#### **UI Components**
- `ButtonActor` - Customizable rectangular buttons
- `AestheticButton` - Rounded cream-colored buttons
- `RoundedButton` - Abstract rounded button base
- `SidebarButton` - Sidebar navigation buttons
- `TextLabel` - Text display component
- `SummaryCard` - Dashboard summary cards

#### **Task System**
- `TaskList` / `ToDoList` - Task collection management
- `TaskData` - Individual task data *(not shown)*
- `TaskCard` - Visual task representation *(not shown)*
- `UrgencyMenu` - Urgency level selector

#### **Calendar System**
- `CalendarPanel` - Main calendar container
- `CalendarMonth` - Month data and navigation
- `CalendarDay` - Individual day component
- `EventItem` - Event display in popup
- `EventPopup` - Day detail view *(not shown)*

#### **School System**
- `SchoolPanel` - School assignments container *(not shown)*
- `SchoolTask` - Assignment data model
- `SchoolTaskCard` - Visual assignment card

#### **Music System**
- `MusicSelector` - Music player UI
- `MusicManager` - Audio playback control *(not shown)*


### Color Scheme
- **Primary**: Navy Blue (`#000080`)
- **Secondary**: Cream/Beige (`#EFE8DB`)
- **Background**: Light Beige (`#EFE8DB`)
- **Accents**: Light Blue, Peach, Soft Yellow

### UI Philosophy
- Clean, minimalist design
- Intuitive navigation
- Visual feedback for all interactions
- Consistent color-coded urgency system
- Soft shadows and rounded corners

## 🔧 Technical Details

### Data Persistence
- User accounts saved between sessions
- Tasks and assignments persist across logins
- Calendar events stored per user
- Notes retained in user profiles

### Sound System
- Optional audio feedback
- Graceful fallback if sound files missing
- Background music playback
- Success/error audio cues

## Project Structure

```
productivity-dashboard/
├── AestheticButton.java
├── ButtonActor.java
├── CalendarDay.java
├── CalendarMonth.java
├── CalendarPanel.java
├── DashboardWorld.java
├── EventItem.java
├── LoginWorld.java
├── MusicSelector.java
├── RoundedButton.java
├── SchoolTask.java
├── SchoolTaskCard.java
├── SidebarButton.java
├── SummaryCard.java
├── TaskList.java
├── TextLabel.java
├── ToDoList.java
├── UrgencyMenu.java
├── UserManager.java
└── sounds/
    ├── *.wav
    └── *.mp3
```

##  License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

##  Acknowledgments

- Built with [Greenfoot](https://www.greenfoot.org/) - Java IDE for education
- Inspired by modern productivity apps
- Music integration for enhanced focus
