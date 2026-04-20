(defn valid_date
  "You have to write a function which validates a given date string and
  returns true if the date is valid otherwise false.
  The date is valid if all of the following rules are satisfied:
  1. The date string is not empty.
  2. The number of days is not less than 1 or higher than 31 days for months 1,3,5,7,8,10,12. And the number of days is not less than 1 or higher than 30 days for months 4,6,9,11. And, the number of days is not less than 1 or higher than 29 for the month 2.
  3. The months should not be less than 1 or higher than 12.
  4. The date should be in the format: mm-dd-yyyy
  >>> (valid_date \"03-11-2000\")
  true
  >>> (valid_date \"15-01-2012\")
  false
  >>> (valid_date \"04-0-2040\")
  false
  >>> (valid_date \"06-04-2020\")
  true
  >>> (valid_date \"06/04/2020\")
  false"
  [date]
  (and (string? date)
       (not (clojure.string/blank? date))
       (let [[_ m d y] (re-matches #"^(\d{1,2})-(\d{1,2})-(\d{4})$" date)]
         (and m d y
              (let [month (Integer/parseInt m)
                    day (Integer/parseInt d)]
                (and (<= 1 month 12)
                     (<= 1 day (get {1 31, 3 31, 5 31, 7 31, 8 31, 10 31, 12 31,
                                     4 30, 6 30, 9 30, 11 30,
                                     2 29}
                                    month 0))))))))