(defn count_distinct_characters
  "Given a string, find out how many distinct characters (regardless of case) does it consist of"
  [string]
  (if (nil? string)
    0
    (-> string
        clojure.string/lower-case
        set
        count)))