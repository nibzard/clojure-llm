(defn digitSum
  "Task
  Write a function that takes a string as input and returns the sum of the upper characters only'
  ASCII codes.
  Examples:
  >>> (digitSum "")
  0
  >>> (digitSum \"abAB\")
  131
  >>> (digitSum \"abcCd\")
  67
  >>> (digitSum \"helloE\")
  69
  >>> (digitSum \"woArBld\")
  131
  >>> (digitSum \"aAaaaXa\")
  153"
  [s]
  (if (nil? s)
    0
    (reduce (fn [sum c] 
              (if (Character/isUpperCase c)
                (+ sum (int c))
                sum))
            0
            s)))