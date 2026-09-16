import { Box, Drawer } from '@mui/material';
import SidebarDrawer from './SidebarDrawer';

const drawerWidth = 240;
const UserSidebar = () => {
    return (
        <Box component={"nav"} sx={{ width: { md: drawerWidth }, flexShrink: { md: 0 } }} >

            {/* Desktop Drawer */}
            <Drawer variant='permanent'
                sx={{
                    display: { xs: "none", md: "block" },
                    "& .MuiDrawer-paper": {
                        boxSizing: "border-box",
                        width: drawerWidth,
                        border: "none",
                    },
                }}
                open={true}
            >
                <SidebarDrawer />



            </Drawer>


        </Box>
    )
}

export default UserSidebar