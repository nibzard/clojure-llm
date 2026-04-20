(defn longest-palindromic-prefix
  "Return the shortest palindrome that ends with the supplied string.

  The function should append the fewest possible characters to the *front*
  of the string so that the result is a palindrome.

  Examples:
  (longest-palindromic-prefix \"\")       ;=> \"\"
  (longest-palindromic-prefix \"cat\")    ;=> \"tacat\"
  (longest-palindromic-prefix \"cata\")   ;=> \"atacata\"

  Notes:
  - Treat nil as the empty string.
  - The input may be a string or any seqable of characters.
  - Return a string."
  [s]
  (let [s (apply str (or s ""))
        n (count s)]
    (if (zero? n)
      ""
      (let [chars (vec s)
            pal? (fn [v]
                   (let [c (count v)]
                     (every? true?
                             (map = v (reverse v)))))
            prefix-len (first
                        (for [i (range n -1 -1)
                              :let [p (subvec chars 0 (inc i))]
                              :when (pal? p)]
                          (count p)))
            suffix (subs s prefix-len)
            prefix-to-add (apply str (reverse suffix))]
        (str prefix-to-add s)))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= "" (longest-palindromic-prefix nil)))
  (is (= "tacat" (longest-palindromic-prefix "cat")))
  (is (= "atacata" (longest-palindromic-prefix "cata")))
  (is (= "racecar" (longest-palindromic-prefix "racecar"))))

(run-test test-variation)
