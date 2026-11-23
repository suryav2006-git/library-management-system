import { useState } from "react";

import {
    Avatar,
    Box,
    Button,
    Chip,
    Dialog,
    DialogActions,
    DialogContent,
    DialogTitle,
    Divider,
    Grid,
    InputAdornment,
    MenuItem,
    Paper,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
    TextField,
    Typography
} from "@mui/material";

import {
    AccountBalanceWallet,
    CheckCircle,
    CurrencyRupee,
    MenuBook,
    PendingActions,
    ReceiptLong,
    Search,
    Visibility
} from "@mui/icons-material";

import "./MyFine.css";


const MyFine = () => {

    const [search, setSearch] = useState("");

    const [filter, setFilter] = useState("All");

    const [selectedFine, setSelectedFine] = useState(null);


    // Sample Fine Data

    const [fines] = useState([

        {
            id: "FINE001",
            book: "The Great Gatsby",
            author: "F. Scott Fitzgerald",
            issueDate: "2026-10-01",
            dueDate: "2026-10-15",
            returnDate: "2026-10-20",
            daysLate: 5,
            amount: 50,
            status: "Pending"
        },

        {
            id: "FINE002",
            book: "Atomic Habits",
            author: "James Clear",
            issueDate: "2026-09-10",
            dueDate: "2026-09-24",
            returnDate: "2026-09-27",
            daysLate: 3,
            amount: 30,
            status: "Paid"
        },

        {
            id: "FINE003",
            book: "Rich Dad Poor Dad",
            author: "Robert Kiyosaki",
            issueDate: "2026-10-05",
            dueDate: "2026-10-19",
            returnDate: "2026-10-25",
            daysLate: 6,
            amount: 60,
            status: "Pending"
        },

        {
            id: "FINE004",
            book: "The Psychology of Money",
            author: "Morgan Housel",
            issueDate: "2026-08-01",
            dueDate: "2026-08-15",
            returnDate: "2026-08-17",
            daysLate: 2,
            amount: 20,
            status: "Paid"
        },

        {
            id: "FINE005",
            book: "Clean Code",
            author: "Robert C. Martin",
            issueDate: "2026-10-10",
            dueDate: "2026-10-24",
            returnDate: null,
            daysLate: 3,
            amount: 30,
            status: "Pending"
        }

    ]);


    // Calculate Statistics

    const totalFines = fines.reduce(
        (total, fine) => total + fine.amount,
        0
    );

    const pendingFines = fines
        .filter(fine => fine.status === "Pending")
        .reduce((total, fine) => total + fine.amount, 0);

    const paidFines = fines
        .filter(fine => fine.status === "Paid")
        .reduce((total, fine) => total + fine.amount, 0);

    const pendingCount = fines.filter(
        fine => fine.status === "Pending"
    ).length;


    // Search and Filter

    const filteredFines = fines.filter(fine => {

        const matchesSearch =
            fine.book.toLowerCase().includes(search.toLowerCase()) ||
            fine.id.toLowerCase().includes(search.toLowerCase());

        const matchesFilter =
            filter === "All" || fine.status === filter;

        return matchesSearch && matchesFilter;

    });


    // Summary Cards

    const statistics = [

        {
            title: "Total Fines",
            value: `₹${totalFines}`,
            icon: <ReceiptLong />,
            color: "#6366f1",
            background: "#eef2ff"
        },

        {
            title: "Pending Fines",
            value: `₹${pendingFines}`,
            icon: <PendingActions />,
            color: "#f59e0b",
            background: "#fef3c7"
        },

        {
            title: "Paid Fines",
            value: `₹${paidFines}`,
            icon: <CheckCircle />,
            color: "#10b981",
            background: "#d1fae5"
        },

        {
            title: "Pending Payments",
            value: pendingCount,
            icon: <CurrencyRupee />,
            color: "#a855f7",
            background: "#f3e8ff"
        }

    ];


    // Status Color

    const getStatusColor = (status) => {

        return status === "Paid" ? "success" : "warning";

    };


    // View Fine Details

    const handleViewDetails = (fine) => {

        setSelectedFine(fine);

    };


    return (

        <Box className="myfine-page">

            {/* PAGE HEADER */}

            <Box className="myfine-header">

                <Box>
                    <Typography variant="h4" fontWeight={700}>
                        My Fines
                    </Typography>

                    <Typography color="text.secondary">
                        Track and manage your library fines and payments.
                    </Typography>
                </Box>

                <Avatar className="myfine-header-icon">
                    <AccountBalanceWallet />
                </Avatar>

            </Box>


            {/* SUMMARY CARDS */}

            <Grid container spacing={2}>

                {statistics.map((item, index) => (

                    <Grid item xs={12} sm={6} md={3} key={index}>

                        <Paper className="myfine-stat-card">

                            <Box
                                className="myfine-stat-icon"
                                sx={{
                                    color: item.color,
                                    backgroundColor: item.background
                                }}
                            >
                                {item.icon}
                            </Box>

                            <Box>

                                <Typography className="myfine-stat-value">
                                    {item.value}
                                </Typography>

                                <Typography className="myfine-stat-title">
                                    {item.title}
                                </Typography>

                            </Box>

                        </Paper>

                    </Grid>

                ))}

            </Grid>


            {/* FINE INFORMATION */}

            <Paper className="myfine-content">

                <Box className="myfine-content-header">

                    <Box>

                        <Typography variant="h6" fontWeight={700}>
                            Fine History
                        </Typography>

                        <Typography variant="body2" color="text.secondary">
                            View all your library fine transactions.
                        </Typography>

                    </Box>

                    <Chip
                        label={`${filteredFines.length} Records`}
                        color="primary"
                        variant="outlined"
                    />

                </Box>


                <Divider sx={{ my: 2 }} />


                {/* SEARCH AND FILTER */}

                <Box className="myfine-toolbar">

                    <TextField
                        placeholder="Search by book name or fine ID..."
                        value={search}
                        onChange={(e) => setSearch(e.target.value)}
                        size="small"
                        className="myfine-search"
                        InputProps={{
                            startAdornment: (
                                <InputAdornment position="start">
                                    <Search />
                                </InputAdornment>
                            )
                        }}
                    />


                    <TextField
                        select
                        label="Payment Status"
                        value={filter}
                        onChange={(e) => setFilter(e.target.value)}
                        size="small"
                        sx={{ minWidth: 160 }}
                    >

                        <MenuItem value="All">All Fines</MenuItem>
                        <MenuItem value="Pending">Pending</MenuItem>
                        <MenuItem value="Paid">Paid</MenuItem>

                    </TextField>

                </Box>


                {/* FINE TABLE */}

                <TableContainer className="myfine-table-container">

                    <Table>

                        <TableHead>

                            <TableRow>

                                <TableCell>Fine ID</TableCell>

                                <TableCell>Book Details</TableCell>

                                <TableCell>Due Date</TableCell>

                                <TableCell>Return Date</TableCell>

                                <TableCell>Days Late</TableCell>

                                <TableCell>Amount</TableCell>

                                <TableCell>Status</TableCell>

                                <TableCell>Action</TableCell>

                            </TableRow>

                        </TableHead>


                        <TableBody>

                            {filteredFines.map((fine) => (

                                <TableRow key={fine.id} hover>

                                    <TableCell>
                                        <Typography
                                            fontWeight={600}
                                            color="primary"
                                        >
                                            {fine.id}
                                        </Typography>
                                    </TableCell>


                                    <TableCell>

                                        <Box className="myfine-book-details">

                                            <Avatar className="myfine-book-icon">
                                                <MenuBook />
                                            </Avatar>

                                            <Box>

                                                <Typography fontWeight={600}>
                                                    {fine.book}
                                                </Typography>

                                                <Typography
                                                    variant="caption"
                                                    color="text.secondary"
                                                >
                                                    {fine.author}
                                                </Typography>

                                            </Box>

                                        </Box>

                                    </TableCell>


                                    <TableCell>
                                        {fine.dueDate}
                                    </TableCell>


                                    <TableCell>
                                        {fine.returnDate || "Not Returned"}
                                    </TableCell>


                                    <TableCell>
                                        <Chip
                                            label={`${fine.daysLate} Days`}
                                            size="small"
                                            color="warning"
                                        />
                                    </TableCell>


                                    <TableCell>

                                        <Typography fontWeight={700}>
                                            ₹{fine.amount}
                                        </Typography>

                                    </TableCell>


                                    <TableCell>

                                        <Chip
                                            label={fine.status}
                                            color={getStatusColor(fine.status)}
                                            size="small"
                                        />

                                    </TableCell>


                                    <TableCell>

                                        <Button
                                            size="small"
                                            variant="outlined"
                                            startIcon={<Visibility />}
                                            onClick={() => handleViewDetails(fine)}
                                        >
                                            View
                                        </Button>

                                    </TableCell>

                                </TableRow>

                            ))}


                            {filteredFines.length === 0 && (

                                <TableRow>

                                    <TableCell colSpan={8} align="center">

                                        <Box sx={{ py: 5 }}>

                                            <ReceiptLong
                                                sx={{
                                                    fontSize: 45,
                                                    color: "#94a3b8"
                                                }}
                                            />

                                            <Typography fontWeight={600}>
                                                No fines found
                                            </Typography>

                                            <Typography
                                                variant="body2"
                                                color="text.secondary"
                                            >
                                                Try changing your search or filter.
                                            </Typography>

                                        </Box>

                                    </TableCell>

                                </TableRow>

                            )}

                        </TableBody>

                    </Table>

                </TableContainer>

            </Paper>


            {/* FINE DETAILS DIALOG */}

            <Dialog
                open={Boolean(selectedFine)}
                onClose={() => setSelectedFine(null)}
                fullWidth
                maxWidth="sm"
            >

                {selectedFine && (

                    <>

                        <DialogTitle>
                            Fine Details
                        </DialogTitle>

                        <DialogContent>

                            <Box className="fine-dialog-header">

                                <Avatar className="fine-dialog-icon">
                                    <ReceiptLong />
                                </Avatar>

                                <Box>

                                    <Typography fontWeight={700}>
                                        {selectedFine.book}
                                    </Typography>

                                    <Typography
                                        variant="body2"
                                        color="text.secondary"
                                    >
                                        Fine ID: {selectedFine.id}
                                    </Typography>

                                </Box>

                            </Box>


                            <Divider sx={{ my: 2 }} />


                            {[
                                ["Author", selectedFine.author],
                                ["Issue Date", selectedFine.issueDate],
                                ["Due Date", selectedFine.dueDate],
                                ["Return Date", selectedFine.returnDate || "Not Returned"],
                                ["Days Late", `${selectedFine.daysLate} Days`],
                                ["Fine Amount", `₹${selectedFine.amount}`],
                                ["Payment Status", selectedFine.status]

                            ].map(([label, value]) => (

                                <Box className="fine-detail-row" key={label}>

                                    <Typography color="text.secondary">
                                        {label}
                                    </Typography>

                                    <Typography fontWeight={600}>
                                        {value}
                                    </Typography>

                                </Box>

                            ))}

                        </DialogContent>


                        <DialogActions sx={{ p: 2 }}>

                            <Button
                                onClick={() => setSelectedFine(null)}
                            >
                                Close
                            </Button>

                        </DialogActions>

                    </>

                )}

            </Dialog>

        </Box>

    );

};

export default MyFine;