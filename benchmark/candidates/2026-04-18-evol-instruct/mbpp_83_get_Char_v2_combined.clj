(defn get-char
  "Return the character obtained by summing the ASCII values of all characters in the given collection of characters, taking the result modulo 26, and mapping 0->\\a, 1->\\b, ..., 25->\\z.

  Accepts any sequence of characters, including lazy sequences.

  Examples:
  (get-char [\\A \\B \\C]) ;=> \\t
  (get-char \"abc\")       ;=> \\s
  (get-char nil)          ;=> \\a"
  [chars]
  (let [total (reduce + 0 (map int (or chars [])))]
    (char (+ (int \a) (mod total 26)))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= \t (get-char [\A \B \C])))
  (is (= \s (get-char "abc")))
  (is (= \a (get-char nil))))

(run-test test-variation)
