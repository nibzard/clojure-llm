(defn unique-transitions
  "Return the values from the input sequence whenever they differ from the previous value, preserving order.

  Works with nil and lazy/infinite sequences.
  >>> (unique-transitions [1 1 2 2 3 1 1])
  [1 2 3 1]
  >>> (unique-transitions [nil nil 4 4 nil nil 5])
  [nil 4 nil 5]"
  [xs]
  (let [step (fn [[prev out] x]
               (if (= x prev)
                 [prev out]
                 [x (conj out x)]))]
    (second
     (reduce step [::none []] xs))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [1 2 3 1] (unique-transitions [1 1 2 2 3 1 1]))))

(run-test test-variation)
