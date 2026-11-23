import { useState } from "react";

import {
    Alert,
    Box,
    Button,
    Chip,
    Dialog,
    DialogActions,
    DialogContent,
    DialogTitle,
    Divider,
    Paper,
    Snackbar,
    Switch,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
    Typography
} from "@mui/material";

import {
    CalendarMonth,
    CheckCircle,
    CreditCard,
    CurrencyRupee,
    History,
    Star,
    WorkspacePremium
} from "@mui/icons-material";

import "./Subscription.css";


const Subscription = () => {

    const [billingYearly, setBillingYearly] = useState(false);

    const [currentPlan, setCurrentPlan] = useState("Free");

    const [selectedPlan, setSelectedPlan] = useState(null);

    const [dialogOpen, setDialogOpen] = useState(false);

    const [snackbar, setSnackbar] = useState(false);


    // Subscription Plans

    const plans = [

        {
            name: "Free",
            description: "Perfect for casual readers.",
            monthlyPrice: 0,
            yearlyPrice: 0,
            color: "#64748b",
            features: [
                "Borrow up to 2 books",
                "Basic book browsing",
                "Standard reading history",
                "Basic recommendations",
                "Email notifications"
            ]
        },

        {
            name: "Basic",
            description: "For regular book readers.",
            monthlyPrice: 99,
            yearlyPrice: 999,
            color: "#6366f1",
            features: [
                "Borrow up to 5 books",
                "Advanced book browsing",
                "Extended reading history",
                "Personalized recommendations",
                "Priority notifications",
                "Reservation access"
            ]
        },

        {
            name: "Premium",
            description: "For passionate book lovers.",
            monthlyPrice: 199,
            yearlyPrice: 1999,
            color: "#9333ea",
            popular: true,
            features: [
                "Borrow up to 10 books",
                "Unlimited book browsing",
                "Complete reading history",
                "AI book recommendations",
                "Priority support",
                "Advanced reservations",
                "Exclusive member benefits",
                "Reading analytics"
            ]
        }

    ];


    // Sample Billing History

    const billingHistory = [

        {
            id: "INV001",
            plan: "Basic",
            date: "01 Oct 2026",
            amount: 99,
            status: "Paid"
        },

        {
            id: "INV002",
            plan: "Basic",
            date: "01 Sep 2026",
            amount: 99,
            status: "Paid"
        },

        {
            id: "INV003",
            plan: "Basic",
            date: "01 Aug 2026",
            amount: 99,
            status: "Paid"
        }

    ];


    // Current Subscription Details

    const subscriptionDetails = {
        startDate: "01 October 2026",
        expiryDate: "30 November 2026",
        paymentMethod: "UPI",
        nextBilling: "01 December 2026"
    };


    // Select Plan

    const handleSelectPlan = (plan) => {

        setSelectedPlan(plan);
        setDialogOpen(true);

    };


    // Confirm Subscription

    const handleConfirmSubscription = () => {

        if (selectedPlan) {

            setCurrentPlan(selectedPlan.name);

            setDialogOpen(false);

            setSnackbar(true);

        }

    };


    // Price Calculation

    const getPrice = (plan) => {

        return billingYearly
            ? plan.yearlyPrice
            : plan.monthlyPrice;

    };


    return (

        <Box className="subscription-page">


            {/* PAGE HEADER */}

            <Box className="subscription-header">

                <Box>

                    <Typography variant="h4" fontWeight={700}>
                        Subscription
                    </Typography>

                    <Typography color="text.secondary">
                        Manage your LibraryHub membership and plans.
                    </Typography>

                </Box>

                <Box className="subscription-header-icon">
                    <WorkspacePremium />
                </Box>

            </Box>



            {/* CURRENT SUBSCRIPTION */}

            <Paper className="current-subscription">

                <Box className="current-subscription-top">

                    <Box className="current-plan-icon">
                        <WorkspacePremium />
                    </Box>

                    <Box>

                        <Typography variant="body2">
                            YOUR CURRENT PLAN
                        </Typography>

                        <Typography variant="h5" fontWeight={700}>
                            {currentPlan} Membership
                        </Typography>

                    </Box>

                    <Chip
                        label="Active"
                        color="success"
                    />

                </Box>


                <Divider sx={{ my: 3, borderColor: "rgba(255,255,255,0.15)" }} />


                <Box className="subscription-details-grid">

                    <Box className="subscription-detail">

                        <CalendarMonth />

                        <Box>

                            <Typography variant="caption">
                                Start Date
                            </Typography>

                            <Typography fontWeight={600}>
                                {subscriptionDetails.startDate}
                            </Typography>

                        </Box>

                    </Box>


                    <Box className="subscription-detail">

                        <CalendarMonth />

                        <Box>

                            <Typography variant="caption">
                                Expiry Date
                            </Typography>

                            <Typography fontWeight={600}>
                                {subscriptionDetails.expiryDate}
                            </Typography>

                        </Box>

                    </Box>


                    <Box className="subscription-detail">

                        <CreditCard />

                        <Box>

                            <Typography variant="caption">
                                Payment Method
                            </Typography>

                            <Typography fontWeight={600}>
                                {subscriptionDetails.paymentMethod}
                            </Typography>

                        </Box>

                    </Box>


                    <Box className="subscription-detail">

                        <CurrencyRupee />

                        <Box>

                            <Typography variant="caption">
                                Next Billing
                            </Typography>

                            <Typography fontWeight={600}>
                                {currentPlan === "Free"
                                    ? "No payment required"
                                    : subscriptionDetails.nextBilling}
                            </Typography>

                        </Box>

                    </Box>

                </Box>

            </Paper>



            {/* PRICING HEADER */}

            <Box className="pricing-header">

                <Typography variant="h5" fontWeight={700}>
                    Choose Your Plan
                </Typography>

                <Typography color="text.secondary">
                    Find the perfect membership for your reading journey.
                </Typography>


                <Box className="billing-toggle">

                    <Typography>
                        Monthly
                    </Typography>

                    <Switch
                        checked={billingYearly}
                        onChange={(e) =>
                            setBillingYearly(e.target.checked)
                        }
                    />

                    <Typography>
                        Yearly
                    </Typography>

                    <Chip
                        label="Save with yearly billing"
                        color="success"
                        size="small"
                    />

                </Box>

            </Box>



            {/* SUBSCRIPTION PLANS */}

            <Box className="subscription-plans">

                {plans.map((plan) => (

                    <Paper
                        key={plan.name}
                        className={`subscription-plan-card ${plan.popular ? "popular-plan" : ""
                            }`}
                    >

                        {plan.popular && (

                            <Chip
                                icon={<Star />}
                                label="Most Popular"
                                className="popular-badge"
                            />

                        )}


                        <Box className="plan-icon" sx={{ color: plan.color }}>
                            <WorkspacePremium />
                        </Box>


                        <Typography variant="h5" fontWeight={700}>
                            {plan.name}
                        </Typography>


                        <Typography
                            color="text.secondary"
                            className="plan-description"
                        >
                            {plan.description}
                        </Typography>


                        <Box className="plan-price">

                            <Typography variant="h3" fontWeight={700}>

                                ₹{getPrice(plan)}

                            </Typography>

                            <Typography color="text.secondary">
                                / {billingYearly ? "year" : "month"}
                            </Typography>

                        </Box>


                        <Divider sx={{ my: 2 }} />


                        <Typography fontWeight={600} sx={{ mb: 2 }}>
                            What's included
                        </Typography>


                        <Box className="plan-features">

                            {plan.features.map((feature, index) => (

                                <Box className="plan-feature" key={index}>

                                    <CheckCircle />

                                    <Typography variant="body2">
                                        {feature}
                                    </Typography>

                                </Box>

                            ))}

                        </Box>


                        <Box className="plan-action">

                            <Button
                                fullWidth
                                variant={
                                    currentPlan === plan.name
                                        ? "outlined"
                                        : "contained"
                                }
                                disabled={currentPlan === plan.name}
                                onClick={() => handleSelectPlan(plan)}
                            >

                                {currentPlan === plan.name
                                    ? "Current Plan"
                                    : plan.monthlyPrice > 0
                                        ? "Choose Plan"
                                        : "Switch to Free"}

                            </Button>

                        </Box>

                    </Paper>

                ))}

            </Box>



            {/* BILLING HISTORY */}

            <Paper className="billing-history">

                <Box className="billing-header">

                    <Box>

                        <Typography variant="h6" fontWeight={700}>
                            Billing History
                        </Typography>

                        <Typography variant="body2" color="text.secondary">
                            View your previous subscription payments.
                        </Typography>

                    </Box>

                    <History sx={{ color: "#6366f1", fontSize: 28 }} />

                </Box>


                <Divider sx={{ my: 2 }} />


                <TableContainer>

                    <Table>

                        <TableHead>

                            <TableRow>

                                <TableCell>Invoice ID</TableCell>

                                <TableCell>Plan</TableCell>

                                <TableCell>Date</TableCell>

                                <TableCell>Amount</TableCell>

                                <TableCell>Status</TableCell>

                            </TableRow>

                        </TableHead>


                        <TableBody>

                            {billingHistory.map((invoice) => (

                                <TableRow key={invoice.id} hover>

                                    <TableCell>
                                        <Typography fontWeight={600} color="primary">
                                            {invoice.id}
                                        </Typography>
                                    </TableCell>

                                    <TableCell>
                                        {invoice.plan}
                                    </TableCell>

                                    <TableCell>
                                        {invoice.date}
                                    </TableCell>

                                    <TableCell>
                                        ₹{invoice.amount}
                                    </TableCell>

                                    <TableCell>

                                        <Chip
                                            label={invoice.status}
                                            color="success"
                                            size="small"
                                        />

                                    </TableCell>

                                </TableRow>

                            ))}

                        </TableBody>

                    </Table>

                </TableContainer>

            </Paper>



            {/* CONFIRMATION DIALOG */}

            <Dialog
                open={dialogOpen}
                onClose={() => setDialogOpen(false)}
                maxWidth="xs"
                fullWidth
            >

                <DialogTitle>
                    Confirm Subscription
                </DialogTitle>

                <DialogContent>

                    {selectedPlan && (

                        <Box sx={{ py: 2 }}>

                            <Typography color="text.secondary">
                                You selected
                            </Typography>

                            <Typography variant="h5" fontWeight={700}>
                                {selectedPlan.name} Plan
                            </Typography>

                            <Typography sx={{ mt: 2 }}>
                                Amount: ₹{getPrice(selectedPlan)}
                            </Typography>

                            <Typography variant="body2" color="text.secondary">
                                Billing: {billingYearly ? "Yearly" : "Monthly"}
                            </Typography>

                        </Box>

                    )}

                </DialogContent>


                <DialogActions sx={{ p: 2 }}>

                    <Button onClick={() => setDialogOpen(false)}>
                        Cancel
                    </Button>

                    <Button
                        variant="contained"
                        onClick={handleConfirmSubscription}
                    >
                        Confirm
                    </Button>

                </DialogActions>

            </Dialog>



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
                    Subscription updated successfully!
                </Alert>

            </Snackbar>


        </Box>

    );

};


export default Subscription;