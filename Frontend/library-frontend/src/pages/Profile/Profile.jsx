import { useState } from "react";

import {
    Alert,
    Avatar,
    Box,
    Button,
    Chip,
    Divider,
    Grid,
    Paper,
    Snackbar,
    TextField,
    Typography
} from "@mui/material";

import {
    Bookmark,
    Edit,
    History,
    LocalLibrary,
    MenuBook,
    Person,
    Save
} from "@mui/icons-material";

import "./Profile.css";


const Profile = () => {

    const [isEditing, setIsEditing] = useState(false);

    const [profile, setProfile] = useState({
        name: "Surya",
        email: "surya@example.com",
        phone: "",
        dob: "",
        gender: "",
        address: "",
        memberId: "LIB2026001",
        membershipDate: "January 2026"
    });

    const [snackbar, setSnackbar] = useState(false);


    // Profile Information Update
    const handleChange = (e) => {

        setProfile({
            ...profile,
            [e.target.name]: e.target.value
        });

    };


    // Save Profile
    const handleSave = () => {

        setIsEditing(false);
        setSnackbar(true);

    };


    // Statistics
    const statistics = [
        {
            title: "Books Borrowed",
            value: "25",
            icon: <MenuBook />,
            color: "#6366f1"
        },
        {
            title: "Books Read",
            value: "20",
            icon: <LocalLibrary />,
            color: "#10b981"
        },
        {
            title: "Active Loans",
            value: "3",
            icon: <History />,
            color: "#f59e0b"
        },
        {
            title: "Reservations",
            value: "2",
            icon: <Bookmark />,
            color: "#a855f7"
        }
    ];


    return (

        <Box className="profile-page">

            {/* PROFILE HEADER */}

            <Paper className="profile-banner">

                <Box className="profile-banner-content">

                    <Avatar className="profile-avatar">
                        {profile.name.charAt(0).toUpperCase()}
                    </Avatar>

                    <Box className="profile-user-details">

                        <Typography variant="h4">
                            {profile.name}
                        </Typography>

                        <Typography>
                            {profile.email}
                        </Typography>

                        <Box className="profile-badges">

                            <Chip
                                label="Active Member"
                                color="success"
                                size="small"
                            />

                            <Chip
                                label="LibraryHub Member"
                                size="small"
                            />

                        </Box>

                    </Box>

                </Box>

                <Button
                    variant="contained"
                    startIcon={<Edit />}
                    onClick={() => setIsEditing(!isEditing)}
                    className="profile-edit-button"
                >
                    {isEditing ? "Cancel" : "Edit Profile"}
                </Button>

            </Paper>


            {/* LIBRARY STATISTICS */}

            <Typography
                variant="h5"
                className="profile-section-title"
            >
                My Library Statistics
            </Typography>


            <Grid container spacing={2}>

                {statistics.map((item, index) => (

                    <Grid item xs={12} sm={6} md={3} key={index}>

                        <Paper className="profile-stat-card">

                            <Box
                                className="profile-stat-icon"
                                sx={{
                                    color: item.color,
                                    backgroundColor: `${item.color}15`
                                }}
                            >
                                {item.icon}
                            </Box>

                            <Box>
                                <Typography className="profile-stat-value">
                                    {item.value}
                                </Typography>

                                <Typography className="profile-stat-title">
                                    {item.title}
                                </Typography>
                            </Box>

                        </Paper>

                    </Grid>

                ))}

            </Grid>


            {/* PERSONAL INFORMATION */}

            <Box className="profile-content-grid">

                <Paper className="profile-information-card">

                    <Box className="profile-card-header">

                        <Box>
                            <Typography variant="h6" fontWeight={700}>
                                Personal Information
                            </Typography>

                            <Typography variant="body2" color="text.secondary">
                                Manage your personal details.
                            </Typography>
                        </Box>

                        <Person sx={{ color: "#6366f1", fontSize: 28 }} />

                    </Box>

                    <Divider sx={{ my: 3 }} />


                    <Box className="profile-form-grid">

                        <TextField
                            label="Full Name"
                            name="name"
                            value={profile.name}
                            onChange={handleChange}
                            disabled={!isEditing}
                            fullWidth
                        />

                        <TextField
                            label="Email Address"
                            name="email"
                            value={profile.email}
                            onChange={handleChange}
                            disabled={!isEditing}
                            fullWidth
                        />

                        <TextField
                            label="Phone Number"
                            name="phone"
                            value={profile.phone}
                            onChange={handleChange}
                            disabled={!isEditing}
                            fullWidth
                        />

                        <TextField
                            label="Date of Birth"
                            name="dob"
                            type="date"
                            value={profile.dob}
                            onChange={handleChange}
                            disabled={!isEditing}
                            fullWidth
                            slotProps={{
                                inputLabel: {
                                    shrink: true
                                }
                            }}
                        />

                        <TextField
                            label="Gender"
                            name="gender"
                            value={profile.gender}
                            onChange={handleChange}
                            disabled={!isEditing}
                            fullWidth
                        />

                        <TextField
                            label="Address"
                            name="address"
                            value={profile.address}
                            onChange={handleChange}
                            disabled={!isEditing}
                            fullWidth
                        />

                    </Box>


                    {isEditing && (

                        <Button
                            variant="contained"
                            startIcon={<Save />}
                            onClick={handleSave}
                            className="profile-save-button"
                        >
                            Save Changes
                        </Button>

                    )}

                </Paper>


                {/* MEMBERSHIP INFORMATION */}

                <Paper className="profile-information-card">

                    <Box className="profile-card-header">

                        <Box>
                            <Typography variant="h6" fontWeight={700}>
                                Membership Information
                            </Typography>

                            <Typography variant="body2" color="text.secondary">
                                Your library membership details.
                            </Typography>
                        </Box>

                        <LocalLibrary
                            sx={{ color: "#6366f1", fontSize: 28 }}
                        />

                    </Box>

                    <Divider sx={{ my: 3 }} />


                    <Box className="membership-details">

                        <Box className="membership-item">

                            <Typography color="text.secondary">
                                Member ID
                            </Typography>

                            <Typography fontWeight={600}>
                                {profile.memberId}
                            </Typography>

                        </Box>


                        <Box className="membership-item">

                            <Typography color="text.secondary">
                                Membership Date
                            </Typography>

                            <Typography fontWeight={600}>
                                {profile.membershipDate}
                            </Typography>

                        </Box>


                        <Box className="membership-item">

                            <Typography color="text.secondary">
                                Account Status
                            </Typography>

                            <Chip
                                label="Active"
                                color="success"
                                size="small"
                            />

                        </Box>


                        <Box className="membership-item">

                            <Typography color="text.secondary">
                                Membership Type
                            </Typography>

                            <Typography fontWeight={600}>
                                Standard Member
                            </Typography>

                        </Box>

                    </Box>

                </Paper>

            </Box>


            {/* RECENT ACTIVITY */}

            <Paper className="profile-activity-card">

                <Box className="profile-card-header">

                    <Box>
                        <Typography variant="h6" fontWeight={700}>
                            Recent Reading Activity
                        </Typography>

                        <Typography variant="body2" color="text.secondary">
                            Your recently borrowed books.
                        </Typography>
                    </Box>

                    <History sx={{ color: "#6366f1", fontSize: 28 }} />

                </Box>

                <Divider sx={{ my: 2 }} />


                {[
                    {
                        title: "The Great Gatsby",
                        author: "F. Scott Fitzgerald",
                        status: "Currently Reading",
                        color: "success"
                    },
                    {
                        title: "Atomic Habits",
                        author: "James Clear",
                        status: "Returned",
                        color: "default"
                    },
                    {
                        title: "Rich Dad Poor Dad",
                        author: "Robert Kiyosaki",
                        status: "Returned",
                        color: "default"
                    }
                ].map((book, index) => (

                    <Box className="profile-activity-item" key={index}>

                        <Avatar
                            sx={{
                                bgcolor: "#eef2ff",
                                color: "#6366f1"
                            }}
                        >
                            <MenuBook />
                        </Avatar>

                        <Box className="profile-activity-details">

                            <Typography fontWeight={600}>
                                {book.title}
                            </Typography>

                            <Typography
                                variant="body2"
                                color="text.secondary"
                            >
                                {book.author}
                            </Typography>

                        </Box>

                        <Chip
                            label={book.status}
                            color={book.color}
                            size="small"
                        />

                    </Box>

                ))}

            </Paper>


            {/* SUCCESS MESSAGE */}

            <Snackbar
                open={snackbar}
                autoHideDuration={3000}
                onClose={() => setSnackbar(false)}
            >
                <Alert
                    severity="success"
                    variant="filled"
                    onClose={() => setSnackbar(false)}
                >
                    Profile updated successfully!
                </Alert>
            </Snackbar>

        </Box>

    );

};

export default Profile;