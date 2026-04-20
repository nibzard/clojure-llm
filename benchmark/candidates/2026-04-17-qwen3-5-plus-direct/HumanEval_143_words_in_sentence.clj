(defn words_in_sentence
  "You are given a string representing a sentence,
  the sentence contains some words separated by a space,
  and you have to return a string that contains the words from the original sentence,
  whose lengths are prime numbers,
  the order of the words in the new string should be the same as the original one.
  Example 1:
  >>> (words_in_sentence "This is a test")
  "is"
  Example 2:
  >>> (words_in_sentence "lets go for swimming")
  "go for"
  Constraints:
      * 1 <= len(sentence) <= 100
      * sentence contains only letters"
  [sentence]
  (let [prime? (fn [n]
                 (if (< n 2)
                   false
                   (every? #(not= 0 (mod n %))
                           (range 2 (inc (int (Math/sqrt n)))))))]
    (->> (clojure.string/split sentence #" ")
         (filter #(prime? (count %)))
         (clojure.string/join " "))))