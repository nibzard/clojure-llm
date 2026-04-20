(defn number_of_substrings
  "Write a cljthon function to count the number of non-empty substrings of a given string."
  [str]
  (let [n (count (or str ""))]
    (quot (* n (inc n)) 2)))