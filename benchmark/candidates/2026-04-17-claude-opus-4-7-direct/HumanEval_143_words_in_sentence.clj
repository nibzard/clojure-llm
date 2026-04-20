(defn words_in_sentence
  "Returns a string containing words from the original sentence whose lengths are prime numbers."
  [sentence]
  (letfn [(prime? [n]
            (or (= n 2)
                (and (> n 2)
                     (odd? n)
                     (not-any? #(zero? (mod n %))
                               (range 3 (inc (Math/sqrt n)) 2)))))]
    (->> (clojure.string/split sentence #"\s+")
         (filter #(prime? (count %)))
         (clojure.string/join " "))))