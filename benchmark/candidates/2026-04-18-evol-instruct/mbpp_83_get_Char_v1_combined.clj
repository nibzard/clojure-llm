(defn xor-char
  "Return a character derived by XOR-ing the character codes in `chars` and mapping the result into A-Z.

  Accepts any sequence of characters, including nil and empty sequences.
  Treat nil as empty.
  Example:
  (xor-char [\\A \\B \\C]) => \\A
  (xor-char nil) => \\A
  (xor-char [\\a \\b]) => a character in the range \\A through \\Z."
  [chars]
  (let [x (reduce bit-xor 0 (map int (or chars [])))]
    (char (+ (int \A) (mod x 26)))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= \A (xor-char nil)))
  (is (= \A (xor-char [])))
  (is (= \A (xor-char [\A \B \C])))
  (is (= \C (xor-char [\A \B])))
  (is (= \Z (xor-char [\a])))
  (is (= \Q (xor-char [\x \y \z]))))

(run-test test-variation)
