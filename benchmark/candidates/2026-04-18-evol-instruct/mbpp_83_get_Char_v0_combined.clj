(defn get-char
  "Given a collection of strings, return the character produced by adding the ASCII values of all characters across all strings, modulo 26, mapping 0 -> \\z and 1 -> \\a through 25 -> \\y.

  Examples:
  (get-char [\"ab\" \"C\"]) ;=> \\e
  (get-char []) ;=> \\z
  (get-char [nil \"A\"]) ;=> \\n"
  [strings]
  (let [sum (reduce
             +
             0
             (mapcat #(when % (seq %)) strings))
        n (mod sum 26)]
    (if (zero? n)
      \z
      (char (+ 96 n)))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= \e (get-char ["ab" "C"])))
  (is (= \z (get-char [])))
  (is (= \n (get-char [nil "A"]))))

(run-test test-variation)
