(defn number_of_substrings
  "Write a cljthon function to count the number of non-empty substrings of a given string."
  [str]
  (let [s (or str "")
        n (count s)]
    (quot (* n (inc n)) 2)))