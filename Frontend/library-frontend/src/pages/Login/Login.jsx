
import { useNavigate } from "react-router";
import { Box, Button, Typography, Paper } from "@mui/material";
import MenuBookIcon from "@mui/icons-material/MenuBook";

const Login = () => {
    const navigate = useNavigate();

    return (
        <Box
            sx={{
                minHeight: "100vh",
                display: "flex",
                alignItems: "center",
                justifyContent: "center",
                background: "linear-gradient(135deg, #1e293b, #312e81)",
                padding: 2,
            }}
        >
            <Paper
                elevation={8}
                sx={{
                    width: "100%",
                    maxWidth: 420,
                    padding: 5,
                    borderRadius: 4,
                    textAlign: "center",
                }}
            >
                <MenuBookIcon
                    sx={{ fontSize: 55, color: "#7c3aed", mb: 2 }}
                />

                <Typography variant="h4" fontWeight="bold">
                    LibraryHub
                </Typography>

                <Typography color="text.secondary" sx={{ mt: 2 }}>
                    You have been logged out successfully.
                </Typography>

                <Button
                    fullWidth
                    variant="contained"
                    sx={{
                        mt: 4,
                        py: 1.5,
                        borderRadius: 2,
                        background: "#7c3aed",
                        "&:hover": {
                            background: "#6d28d9",
                        },
                    }}
                    onClick={() => navigate("/")}
                >
                    Back to Dashboard
                </Button>
            </Paper>
        </Box>
    );
};

export default Login;
