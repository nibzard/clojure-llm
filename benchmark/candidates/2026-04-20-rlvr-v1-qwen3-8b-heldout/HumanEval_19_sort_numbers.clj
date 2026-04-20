(defn sort_numbers
  " Input is a space-delimited string of numberals from 'zero' to 'nine'.
  Valid choices are 'zero', 'one', 'two', 'three', 'four', 'five', 'six', 'seven', 'eight' and 'nine'.
  Return the string with numbers sorted from smallest to largest
  >>> (sort_numbers \"three one five\")
  \"one three five\""
  [numbers]
  (let [digit-words {'zero' 0 'one' 1 'two' 2 'three' 3 'four' 4 'five' 5 'six' 6 'seven' 7 'eight' 8 'nine' 9}]
    (->> numbers
         clojure.string/split
         (sort (fn [a b] (compare (digit-words a) (digit-words b))))
         (mapv #(for [k digit-words :when (= (digit-words k) %)] k))
         (map first)
         (clojure.string/join " "))))