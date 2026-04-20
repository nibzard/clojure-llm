(defn problem-juxtaposition
  [& fs]
  (fn [& args]
    (vec (map #(apply % args) fs))))

(require '[clojure.test :refer [deftest is run-tests]])

(deftest test-problem-juxtaposition
  (is (= (= [21 6 1] ((problem-juxtaposition + max min) 2 3 5 1 6 4))))
  (is (= (= ["HELLO" 5] ((problem-juxtaposition #(.toUpperCase %) count) "hello"))))
  (is (= (= [2 6 4] ((problem-juxtaposition :a :c :b) {:a 2, :b 4, :c 6, :d 8 :e 10})))))

(run-tests)
