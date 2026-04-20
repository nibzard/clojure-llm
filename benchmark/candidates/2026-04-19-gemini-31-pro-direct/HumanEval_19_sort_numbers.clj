(defn sort_numbers
  " Input is a space-delimited string of numberals from 'zero' to 'nine'.
  Valid choices are 'zero', 'one', 'two', 'three', 'four', 'five', 'six', 'seven', 'eight' and 'nine'.
  Return the string with numbers sorted from smallest to largest"
  [numbers]
  (if (clojure.string/blank? numbers)
    ""
    (let [word->num {"zero" 0 "one" 1 "two" 2 "three" 3 "four" 4
                     "five" 5 "six" 6 "seven" 7 "eight" 8 "nine" 9}]
      (->> (clojure.string/split numbers #"\s+")
           (sort-by word->num)
           (clojure.string/join " ")))))