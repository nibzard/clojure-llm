(defn remove_length
  "Write a function to remove all the words with k length in the given string."
  [test_str K]
  (let [s (or test_str "")
        k (if (number? K) K (try (Long/parseLong (str K)) (catch Exception _ nil)))]
    (if (or (nil? k) (empty? s))
      s
      (->> (re-seq #"\S+" s)
           (remove #(= (count %) k))
           (clojure.string/join " ")))))