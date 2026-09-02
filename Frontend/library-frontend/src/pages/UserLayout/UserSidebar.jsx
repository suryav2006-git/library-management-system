import { Box, Drawer } from '@mui/material';
import SideBarDrawer from './SideBarDrawer';

const drawerWidth = "240px";
const UserSidebar = () => {
    return (
        <Box component={"nav"} sx={{ width: { md: drawerWidth }, flexShrink: { md: 0 } }} >

            {/* Desktop Sidebar */}
            <Drawer
                variant='permanent'
                sx={{
                    display: { xs: "none", md: "block" },
                    "& .MuiDrawer-paper": {
                        boxSizing: "border-box",
                        width: drawerWidth,
                        border: "none",
                    },
                }}
                open
            >
                <SideBarDrawer />
            </Drawer>

        </Box>
    )
}

export default UserSidebar