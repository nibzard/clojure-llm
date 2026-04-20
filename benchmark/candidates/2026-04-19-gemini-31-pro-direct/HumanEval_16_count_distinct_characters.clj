(defn count_distinct_characters
  "Given a string, find out how many distinct characters (regardless of case) does it consist of"
  [string]
  (count (set (clojure.string/lower-case (str string)))))