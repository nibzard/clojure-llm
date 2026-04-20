(defn balanced-delimiters?
  "Return true if a string or sequence has balanced (), {}, and [] delimiters.
  Ignores all non-delimiter characters.

  Examples:
  (balanced-delimiters? \"(a+[b*c]-{d/e})\") => true
  (balanced-delimiters? \"([)]\") => false
  (balanced-delimiters? nil) => true"
  [x]
  (let [pairs {\) \( \] \[ \} \{}
        opens (set (vals pairs))
        chars (if (nil? x) [] x)]
    (loop [cs chars
           stack []]
      (if-let [c (first cs)]
        (cond
          (opens c) (recur (rest cs) (conj stack c))
          (contains? pairs c) (if (= (peek stack) (pairs c))
                                (recur (rest cs) (pop stack))
                                false)
          :else (recur (rest cs) stack))
        (empty? stack)))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (balanced-delimiters? "(a+[b*c]-{d/e})"))))

(run-test test-variation)
