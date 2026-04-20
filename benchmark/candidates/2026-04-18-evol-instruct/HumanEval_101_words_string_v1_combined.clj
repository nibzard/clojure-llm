(defn parse-tags
  "You will be given a string containing tags separated by commas, spaces, or mixed punctuation.
  Return a lazy sequence of unique, lower-cased tags in the order they first appear, ignoring nil and blank input.

  Examples:
  >>> (parse-tags \"Clojure, Lisp clojure; FP, lisp\")
  (\"clojure\" \"lisp\" \"fp\")
  >>> (parse-tags nil)
  ()
  >>> (parse-tags \"  cats, dogs; cats  birds \")
  (\"cats\" \"dogs\" \"birds\")"
  [s]
  (when (seq s)
    (->> (clojure.string/split s #"[,\s;]+")
         (map clojure.string/lower-case)
         (remove clojure.string/blank?)
         distinct)))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= '("clojure" "lisp" "fp")
         (parse-tags "Clojure, Lisp clojure; FP, lisp")))
  (is (= '()
         (parse-tags nil)))
  (is (= '("cats" "dogs" "birds")
         (parse-tags "  cats, dogs; cats  birds "))))

(run-test test-variation)
