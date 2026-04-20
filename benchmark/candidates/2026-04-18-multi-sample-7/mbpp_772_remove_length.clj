(defn remove_length
  "	Write a function to remove all the words with k length in the given string."
  [test_str K]
  (let [s (or test_str "")]
    (->> (clojure.string/split s #"\s+")
         (remove #(= (count %) K))
         (remove clojure.string/blank?)
         (clojure.string/join " "))))