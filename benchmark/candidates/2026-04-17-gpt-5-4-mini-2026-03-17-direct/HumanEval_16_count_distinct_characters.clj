(defn count_distinct_characters
  " Given a string, find out how many distinct characters (regardless of case) does it consist of
  >>> (count_distinct_characters \"xyzXYZ\")
  3
  >>> (count_distinct_characters \"Jerry\")
  4"
  [string]
  (count (set (map clojure.string/lower-case string))))