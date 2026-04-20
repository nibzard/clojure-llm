(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [\a \: \b \: \c \:] (vec (normalize-punctuation [\a \space \b \, \c \.]))))
  (is (= [\h \e \l \l \o \: \w \o \r \l \d] (vec (normalize-punctuation "hello world"))))
  (is (= [\a \: \a \: \a]
         (take 5 (normalize-punctuation (cycle [\a \space]))))))

(run-test test-variation)
