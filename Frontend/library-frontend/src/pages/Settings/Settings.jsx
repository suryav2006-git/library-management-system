import { useState } from "react";

import {
    Alert,
    Avatar,
    Box,
    Button,
    Chip,
    Divider,
    MenuItem,
    Paper,
    Snackbar,
    Switch,
    TextField,
    Typography
} from "@mui/material";

import {
    Help,
    Language,
    ManageAccounts,
    Notifications,
    Palette,
    Person,
    Save,
    Security
} from "@mui/icons-material";

import "./Settings.css";


const Settings = () => {

    const [activeTab, setActiveTab] = useState("profile");

    const [profile, setProfile] = useState({
        name: "Surya",
        email: "surya@example.com",
        phone: "",
        bio: "LibraryHub Member"
    });

    const [notifications, setNotifications] = useState({
        email: true,
        dueDate: true,
        reservations: true,
        newBooks: false
    });

    const [appearance, setAppearance] = useState({
        darkMode: false
    });

    const [security, setSecurity] = useState({
        twoFactor: false
    });

    const [preferences, setPreferences] = useState({
        language: "English"
    });

    const [snackbar, setSnackbar] = useState(false);


    const settingsMenu = [
        {
            title: "General",
            items: [
                { id: "profile", label: "My Profile", icon: <Person /> },
                { id: "account", label: "Account", icon: <ManageAccounts /> }
            ]
        },
        {
            title: "Preferences",
            items: [
                { id: "appearance", label: "Appearance", icon: <Palette /> },
                { id: "notifications", label: "Notifications", icon: <Notifications /> },
                { id: "preferences", label: "Language", icon: <Language /> }
            ]
        },
        {
            title: "Security & Support",
            items: [
                { id: "security", label: "Security", icon: <Security /> },
                { id: "support", label: "Help & Support", icon: <Help /> }
            ]
        }
    ];


    const handleProfileChange = (e) => {
        setProfile({
            ...profile,
            [e.target.name]: e.target.value
        });
    };


    const handleSave = (e) => {
        e.preventDefault();
        setSnackbar(true);
    };


    const renderContent = () => {

        switch (activeTab) {

            // PROFILE SETTINGS
            case "profile":
                return (
                    <Box>
                        <Typography variant="h5" className="settings-heading">
                            My Profile
                        </Typography>

                        <Typography className="settings-description">
                            Manage your personal information.
                        </Typography>

                        <Divider sx={{ my: 3 }} />

                        <Box className="profile-avatar-section">

                            <Avatar
                                sx={{
                                    width: 90,
                                    height: 90,
                                    bgcolor: "#6366f1",
                                    fontSize: 35
                                }}
                            >
                                {profile.name.charAt(0).toUpperCase()}
                            </Avatar>

                            <Box>
                                <Typography fontWeight={600}>
                                    Profile Picture
                                </Typography>

                                <Typography variant="body2" color="text.secondary">
                                    Your profile information is visible to your account.
                                </Typography>
                            </Box>

                        </Box>

                        <form onSubmit={handleSave}>

                            <Box className="settings-form-grid">

                                <TextField
                                    label="Full Name"
                                    name="name"
                                    value={profile.name}
                                    onChange={handleProfileChange}
                                    fullWidth
                                />

                                <TextField
                                    label="Email Address"
                                    name="email"
                                    value={profile.email}
                                    onChange={handleProfileChange}
                                    fullWidth
                                />

                                <TextField
                                    label="Phone Number"
                                    name="phone"
                                    value={profile.phone}
                                    onChange={handleProfileChange}
                                    fullWidth
                                />

                                <TextField
                                    label="Bio"
                                    name="bio"
                                    value={profile.bio}
                                    onChange={handleProfileChange}
                                    fullWidth
                                />

                            </Box>

                            <Button
                                type="submit"
                                variant="contained"
                                startIcon={<Save />}
                                className="settings-save-button"
                            >
                                Save Changes
                            </Button>

                        </form>
                    </Box>
                );


            // ACCOUNT SETTINGS
            case "account":
                return (
                    <Box>
                        <Typography variant="h5" className="settings-heading">
                            Account Settings
                        </Typography>

                        <Typography className="settings-description">
                            Manage your account information.
                        </Typography>

                        <Divider sx={{ my: 3 }} />

                        <Box className="settings-form-grid">

                            <TextField
                                label="Username"
                                defaultValue={profile.name}
                                fullWidth
                            />

                            <TextField
                                label="Email"
                                defaultValue={profile.email}
                                fullWidth
                            />

                            <TextField
                                label="Current Password"
                                type="password"
                                fullWidth
                            />

                            <TextField
                                label="New Password"
                                type="password"
                                fullWidth
                            />

                        </Box>

                        <Button
                            variant="contained"
                            startIcon={<Save />}
                            className="settings-save-button"
                            onClick={() => setSnackbar(true)}
                        >
                            Update Account
                        </Button>

                    </Box>
                );


            // APPEARANCE SETTINGS
            case "appearance":
                return (
                    <Box>
                        <Typography variant="h5" className="settings-heading">
                            Appearance
                        </Typography>

                        <Typography className="settings-description">
                            Customize your LibraryHub experience.
                        </Typography>

                        <Divider sx={{ my: 3 }} />

                        <Box className="settings-option">

                            <Box>
                                <Typography fontWeight={600}>
                                    Dark Mode
                                </Typography>

                                <Typography variant="body2" color="text.secondary">
                                    Enable dark appearance.
                                </Typography>
                            </Box>

                            <Switch
                                checked={appearance.darkMode}
                                onChange={(e) =>
                                    setAppearance({
                                        ...appearance,
                                        darkMode: e.target.checked
                                    })
                                }
                            />

                        </Box>

                        <Chip
                            label={appearance.darkMode ? "Dark Mode Selected" : "Light Mode Selected"}
                            color="primary"
                            sx={{ mt: 3 }}
                        />

                    </Box>
                );


            // NOTIFICATION SETTINGS
            case "notifications":
                return (
                    <Box>
                        <Typography variant="h5" className="settings-heading">
                            Notifications
                        </Typography>

                        <Typography className="settings-description">
                            Choose which notifications you want to receive.
                        </Typography>

                        <Divider sx={{ my: 3 }} />

                        {[
                            {
                                id: "email",
                                title: "Email Notifications",
                                description: "Receive updates through email."
                            },
                            {
                                id: "dueDate",
                                title: "Book Due Date Reminders",
                                description: "Get reminders before book return dates."
                            },
                            {
                                id: "reservations",
                                title: "Reservation Updates",
                                description: "Receive updates about your reservations."
                            },
                            {
                                id: "newBooks",
                                title: "New Book Notifications",
                                description: "Get notified about newly added books."
                            }
                        ].map((item) => (

                            <Box className="settings-option" key={item.id}>

                                <Box>
                                    <Typography fontWeight={600}>
                                        {item.title}
                                    </Typography>

                                    <Typography variant="body2" color="text.secondary">
                                        {item.description}
                                    </Typography>
                                </Box>

                                <Switch
                                    checked={notifications[item.id]}
                                    onChange={(e) =>
                                        setNotifications({
                                            ...notifications,
                                            [item.id]: e.target.checked
                                        })
                                    }
                                />

                            </Box>

                        ))}

                    </Box>
                );


            // SECURITY SETTINGS
            case "security":
                return (
                    <Box>
                        <Typography variant="h5" className="settings-heading">
                            Security & Privacy
                        </Typography>

                        <Typography className="settings-description">
                            Keep your LibraryHub account secure.
                        </Typography>

                        <Divider sx={{ my: 3 }} />

                        <Box className="settings-option">

                            <Box>
                                <Typography fontWeight={600}>
                                    Two-Factor Authentication
                                </Typography>

                                <Typography variant="body2" color="text.secondary">
                                    Add an extra layer of account security.
                                </Typography>
                            </Box>

                            <Switch
                                checked={security.twoFactor}
                                onChange={(e) =>
                                    setSecurity({
                                        ...security,
                                        twoFactor: e.target.checked
                                    })
                                }
                            />

                        </Box>

                        <Box className="settings-option">

                            <Box>
                                <Typography fontWeight={600}>
                                    Account Status
                                </Typography>

                                <Typography variant="body2" color="text.secondary">
                                    Your account is currently active.
                                </Typography>
                            </Box>

                            <Chip label="Active" color="success" />

                        </Box>

                    </Box>
                );


            // LANGUAGE SETTINGS
            case "preferences":
                return (
                    <Box>
                        <Typography variant="h5" className="settings-heading">
                            Language Preferences
                        </Typography>

                        <Typography className="settings-description">
                            Customize your application preferences.
                        </Typography>

                        <Divider sx={{ my: 3 }} />

                        <TextField
                            select
                            label="Select Language"
                            value={preferences.language}
                            onChange={(e) =>
                                setPreferences({
                                    ...preferences,
                                    language: e.target.value
                                })
                            }
                            fullWidth
                        >
                            <MenuItem value="English">English</MenuItem>
                            <MenuItem value="Tamil">Tamil</MenuItem>
                            <MenuItem value="Hindi">Hindi</MenuItem>
                        </TextField>

                    </Box>
                );


            // SUPPORT SETTINGS
            case "support":
                return (
                    <Box>
                        <Typography variant="h5" className="settings-heading">
                            Help & Support
                        </Typography>

                        <Typography className="settings-description">
                            Need help using LibraryHub?
                        </Typography>

                        <Divider sx={{ my: 3 }} />

                        <Paper className="support-card">
                            <Help sx={{ fontSize: 45, color: "#6366f1" }} />

                            <Typography variant="h6" sx={{ mt: 2 }}>
                                LibraryHub Support
                            </Typography>

                            <Typography color="text.secondary" sx={{ mt: 1 }}>
                                Get assistance with your library account,
                                borrowing, reservations and other services.
                            </Typography>

                            <Typography sx={{ mt: 2 }}>
                                Application: LibraryHub
                            </Typography>

                            <Typography>
                                Version: 1.0.0
                            </Typography>

                        </Paper>

                    </Box>
                );


            default:
                return null;
        }
    };


    return (

        <Box className="settings-page">

            {/* PAGE HEADER */}

            <Box className="settings-header">

                <Box>
                    <Typography variant="h4" fontWeight={700}>
                        Settings
                    </Typography>

                    <Typography color="text.secondary">
                        Manage your LibraryHub account and preferences.
                    </Typography>
                </Box>

                <Avatar sx={{ bgcolor: "#6366f1", width: 48, height: 48 }}>
                    {profile.name.charAt(0).toUpperCase()}
                </Avatar>

            </Box>


            {/* SETTINGS LAYOUT */}

            <Box className="settings-layout">

                {/* SETTINGS SIDEBAR */}

                <Paper className="settings-navigation">

                    {settingsMenu.map((group) => (

                        <Box key={group.title}>

                            <Typography className="settings-menu-title">
                                {group.title}
                            </Typography>

                            {group.items.map((item) => (

                                <Box
                                    key={item.id}
                                    className={`settings-menu-item ${activeTab === item.id ? "active" : ""
                                        }`}
                                    onClick={() => setActiveTab(item.id)}
                                >

                                    {item.icon}

                                    <Typography>
                                        {item.label}
                                    </Typography>

                                </Box>

                            ))}

                        </Box>

                    ))}

                </Paper>


                {/* SETTINGS CONTENT */}

                <Paper className="settings-content">

                    {renderContent()}

                </Paper>

            </Box>


            {/* SUCCESS MESSAGE */}

            <Snackbar
                open={snackbar}
                autoHideDuration={3000}
                onClose={() => setSnackbar(false)}
            >
                <Alert
                    severity="success"
                    onClose={() => setSnackbar(false)}
                    variant="filled"
                >
                    Settings updated successfully!
                </Alert>
            </Snackbar>

        </Box>

    );
};

export default Settings;