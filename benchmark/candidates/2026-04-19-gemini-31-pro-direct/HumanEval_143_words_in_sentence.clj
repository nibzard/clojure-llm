(defn words_in_sentence [sentence]
  (let [prime? (fn [n]
                 (if (< n 2)
                   false
                   (not-any? #(zero? (mod n %)) (range 2 (inc (int (Math/sqrt n)))))))]
    (->> (clojure.string/split sentence #" ")
         (filter #(prime? (count %)))
         (clojure.string/join " "))))