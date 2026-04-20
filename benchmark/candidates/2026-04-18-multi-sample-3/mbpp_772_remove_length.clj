(defn remove_length
  "	Write a function to remove all the words with k length in the given string."
  [test_str K]
  (let [s (or test_str "")
        k (when (number? K) K)]
    (->> (clojure.string/split s #"\s+")
         (remove #(or (clojure.string/blank? %) (= (count %) k)))
         (clojure.string/join " "))))