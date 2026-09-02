import { Box, Toolbar } from '@mui/material';
import { Outlet } from 'react-router';
import UserSidebar from './UserSidebar';

const drawerWidth = 240;
const UserLayout = () => {
    return (
        <Box sx={{
            display: "flex",
            minHeight: "100vh",
            bgcolor: "white"
        }} >

            {/* App Bar */}

            {/* Profile Menu */}

            {/* User Sidebar */}
            <UserSidebar />


            {/* Main Content */}

            <Box component="main" sx={{
                flexGrow: 1,
                width: { md: `calc(100% - ${drawerWidth}px)` },
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