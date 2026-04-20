(defn parse_csv_numbers
  "Parse a CSV string of integers into a vector of numbers.

  Rules:
  - Numbers may have optional surrounding whitespace.
  - Empty fields are allowed and should be ignored.
  - Nil input should return an empty vector.

  Examples:
  >>> (parse_csv_numbers \"1, 2,3, 4\")
  [1 2 3 4]
  >>> (parse_csv_numbers \"10,, 20 , ,30\")
  [10 20 30]
  >>> (parse_csv_numbers nil)
  []"
  [csv-string]
  (if (nil? csv-string)
    []
    (->> (clojure.string/split csv-string #",")
         (map clojure.string/trim)
         (remove clojure.string/blank?)
         (mapv #(Integer/parseInt %)))))