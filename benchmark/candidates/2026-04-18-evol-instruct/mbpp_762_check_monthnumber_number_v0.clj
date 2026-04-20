(defn month-has-30-days?
  "Return true if month-n is a month with 30 days, false otherwise.
   Accepts integers 1-12. Treat nil, out-of-range values, and non-integers as false.

   Examples:
   (month-has-30-days? 4)  ;=> true
   (month-has-30-days? 2)  ;=> false
   (month-has-30-days? nil) ;=> false"
  [month-n]
  (contains? #{4 6 9 11} month-n))