import { Box, Toolbar } from "@mui/material";
import { Outlet } from "react-router";
import UserSidebar from "./UserSidebar";

const drawerWidth = 280;

const UserLayout = () => {
    return (
        <Box sx={{
            display: "flex",
            minHeight: "100vh",
            bgcolor: "white",
        }} >

            {/* App Bar */}


            {/* Profile Menu */}

            {/* User Sidde bar */}
            <UserSidebar />


            {/* main content */}
            <Box component="main" sx={{
                flexGrow: 1, width: { md: `calc(100% - ${drawerWidth}px)` },
                p: 2,
                minHeight: "100vh"
            }} >
                <Toolbar />
                <Box>
                    <Outlet />
                </Box>
            </Box>

        </Box>
    )
}

export default UserLayout