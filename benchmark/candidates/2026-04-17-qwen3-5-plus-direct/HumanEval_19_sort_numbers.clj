(defn sort_numbers
  [numbers]
  (if (clojure.string/blank? numbers)
    ""
    (let [order (zipmap ["zero" "one" "two" "three" "four" "five" "six" "seven" "eight" "nine"] (range))
          words (filter seq (clojure.string/split numbers #" "))]
      (clojure.string/join " " (sort-by order words)))))