(defn sort_numbers
  " Input is a space-delimited string of numberals from 'zero' to 'nine'.
  Valid choices are 'zero', 'one', 'two', 'three', 'four', 'five', 'six', 'seven', 'eight' and 'nine'.
  Return the string with numbers sorted from smallest to largest
  >>> (sort_numbers \"three one five\")
  \"one three five\""
  [numbers]
  (let [number-words ["zero" "one" "two" "three" "four" "five" "six" "seven" "eight" "nine"]]
    (->> numbers
         (re-seq #"\w+")
         (mapv #(nth number-words (count (filter #(= % %) %))))
         (sort)
         (mapv #(nth number-words (count (filter #(= % %) %))))
         (clojure.string/join " "))))