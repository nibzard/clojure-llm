(defn remove_length
  "	Write a function to remove all the words with k length in the given string."
  [test_str K]
  (->> (clojure.string/split test_str #"\s+")
       (remove #(= (count %) K))
       (clojure.string/join " ")))