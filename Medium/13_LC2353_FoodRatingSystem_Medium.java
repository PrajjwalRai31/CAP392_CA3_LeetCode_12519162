class FoodRatings {
    HashMap<String, String> cuisineMap = new HashMap<>();
    HashMap<String, Integer> ratingMap = new HashMap<>();
    HashMap<String, TreeSet<String>> map = new HashMap<>();

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        for (int i = 0; i < foods.length; i++) {
            cuisineMap.put(foods[i], cuisines[i]);
            ratingMap.put(foods[i], ratings[i]);
            if (!map.containsKey(cuisines[i])) {
                map.put(cuisines[i], new TreeSet<>((a, b) -> {
                    if (!ratingMap.get(a).equals(ratingMap.get(b))) {
                        return ratingMap.get(b) - ratingMap.get(a);
                    }
                    return a.compareTo(b);
                }));
            }
            map.get(cuisines[i]).add(foods[i]);
        }
    }
    public void changeRating(String food, int newRating) {
        String cuisine = cuisineMap.get(food);
        map.get(cuisine).remove(food);
        ratingMap.put(food, newRating);
        map.get(cuisine).add(food);
    }

    public String highestRated(String cuisine) {
        return map.get(cuisine).first();
    }
}
