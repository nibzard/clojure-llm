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
  (let [prime? (fn [n]
                 (and (> n 1)
                      (not-any? #(zero? (mod n %))
                                (range 2 (inc (long (Math/sqrt n)))))))
        words (re-seq #"\w+" sentence)]
    (apply str
           (interpose " "
                      (filter #(prime? (count %)) words)))))