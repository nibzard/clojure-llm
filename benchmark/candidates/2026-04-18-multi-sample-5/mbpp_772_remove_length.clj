(defn remove_length
  "	Write a function to remove all the words with k length in the given string."
  [test_str K]
  (let [s (or test_str "")
        words (re-seq #"\S+" s)]
    (->> words
         (remove #(= (count %) K))
         (clojure.string/join " "))))