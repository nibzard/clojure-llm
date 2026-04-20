(defn words_in_sentence
  "You are given a string representing a sentence,
  the sentence contains some words separated by a space,
  and you have to return a string that contains the words from the original sentence,
  whose lengths are prime numbers,
  the order of the words in the new string should be the same as the original one.
  Example 1:
  >>> (words_in_sentence \"This is a test\")
  \"is\"
  Example 2:
  >>> (words_in_sentence \"lets go for swimming\")
  \"go for\"
  Constraints:
      * 1 <= len(sentence) <= 100
      * sentence contains only letters"
  [sentence]
  (letfn [(prime? [n]
            (cond
              (< n 2) false
              (= n 2) true
              (even? n) false
              :else (not-any? #(zero? (mod n %))
                              (range 3 (inc (long (Math/sqrt n))) 2))))]
    (->> (when (string? sentence)
           (clojure.string/split sentence #"\s+"))
         (filter #(prime? (count %)))
         (clojure.string/join " "))))