(defn words_in_sentence
  [sentence]
  (letfn [(prime? [n]
            (and (> n 1)
                 (not-any? #(zero? (mod n %))
                           (range 2 (inc (Math/sqrt n))))))]
    (->> (clojure.string/split sentence #"\s+")
         (filter #(prime? (count %)))
         (clojure.string/join " "))))