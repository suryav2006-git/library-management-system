import { Box, Card, Tab, Tabs } from "@mui/material";
import React from 'react';
import LoanCard from "./LoanCard.jsx";
import { loans } from "./loans.js";
import { tabs } from './tabs.js';



const MyLoans = () => {

    const [activeTab, setActiveTab] = React.useState(0);


    return (
        <div>

            <div className="min-h-screen bg-gradient-to-br from-indigo-50 via-white to-purple-500 py-8" >

                <div className="px-4 sm:px-6 lg:px-8 " >

                    {/* Header */}
                    <div className="mb-8" >

                        <h1 className="text-4xl font-bold text-gray-900 mb-2 flex items-center space-x-3">
                            <span className="bg-gradient-to-r from-indigo-600 to-purple-600 bg-clip-text text-transparent" >
                                My Borrowed Books
                            </span>

                        </h1>
                        <p className="text-lg text-gray-600" >
                            Manage Your Book Loans, Track Due Dates, And Renew Books
                        </p>

                    </div>

                    {/* Tabs */}
                    <Card className="mb-6" >
                        <Box sx={{
                            BorderBottom: 1,
                            BorderColor: 'divider'
                        }} >
                            <Tabs value={activeTab} onChange={(e, newValue) => setActiveTab(newValue)} aria-label="loan tabs" >
                                {tabs.map((tab) => <Tab key={tab.label} label={tab.label} />)}
                            </Tabs>

                        </Box>
                    </Card>

                    {/*  Loan List*/}
                    <div className="space-y-4 " >
                        {loans.map((loan) => <LoanCard key={loan.id} loan={loan} />)}
                    </div>


                </div>

            </div>

        </div>
    )
}

export default MyLoans