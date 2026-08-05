class Solution {
    public void dfs(int curr, List<List<Integer>> adj, int[] inDegree, boolean[] suspicious) {
        suspicious[curr] = true;
        for(int ngbr : adj.get(curr)) {
            inDegree[ngbr]--;
            if(!suspicious[ngbr]) {
                dfs(ngbr, adj, inDegree, suspicious);
            }
        }
    }

    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        //O(V+E), V = nodes , E = edges
        //graph - adjacency list
        List<List<Integer>> adj = new ArrayList<>(); //u -> {ngbr1, ngbr2} //O(V+E)
        for(int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        int[] inDegree = new int[n];        //O(V)
        boolean[] suspicious = new boolean[n]; //O(V)

        for(int[] edge : invocations) {
            int u = edge[0];
            int v = edge[1];
            adj.get(u).add(v);
            inDegree[v]++;
        }

        //DFS
        dfs(k, adj, inDegree, suspicious);

        List<Integer> result = new ArrayList<>();
        boolean cannotRemove = false;

        for(int i = 0; i < n; i++) {
            if(suspicious[i] && inDegree[i] > 0) {
                cannotRemove = true;
                break;
            }
            if(!suspicious[i]) {
                result.add(i);
            }
        }

        if(cannotRemove) {
            List<Integer> vec = new ArrayList<>(); //0, 1, 2,... n-1
            for(int i = 0; i < n; i++) {
                vec.add(i);
            }
            return vec;
        }

        return result;
    }
}